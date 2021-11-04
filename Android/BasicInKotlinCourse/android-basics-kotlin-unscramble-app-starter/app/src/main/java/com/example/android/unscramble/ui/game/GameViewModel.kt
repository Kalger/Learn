package com.example.android.unscramble.ui.game

import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.TtsSpan
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

private const val TAG = "GameFragment"
/**
 * ViewModel containing the app data and methods to process the data
 */
class GameViewModel : ViewModel() {

    private var wordList: MutableList<String> = mutableListOf()
    private lateinit var curWord: String

    // backing property
    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score
    private val _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount
    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: LiveData<Spannable> = Transformations.map(_currentScrambledWord) {
        if (it == null) {
            SpannableString("")
        } else {
            val scrambledWord = it
            val spannable: Spannable = SpannableString(scrambledWord)
            spannable.setSpan(
                TtsSpan.VerbatimBuilder(scrambledWord).build(),
                0,
                scrambledWord.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            spannable
        }
    }

    init {
        Log.d(TAG, "GameViewModel init!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "GameViewModel onCleared!")
    }

    private fun getNextWord(){
        curWord = allWordsList.random()

        if (wordList.contains(curWord)) {
            getNextWord()
            return
        } else {
            val tmpWord = curWord.toCharArray()

            while (tmpWord.concatToString() == curWord){
                tmpWord.shuffle()
            }
            _currentScrambledWord.value = String(tmpWord)
            _currentWordCount.value = _currentWordCount.value?.inc()
            wordList.add(curWord)
        }
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score.value = _score.value?.plus(SCORE_INCREASE)
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        return if (playerWord.equals(curWord, true)) {
            increaseScore()
            true
        } else false
    }

    /*
    * Re-initializes the game data to restart the game.
    */
    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordList.clear()
        getNextWord()
    }
}