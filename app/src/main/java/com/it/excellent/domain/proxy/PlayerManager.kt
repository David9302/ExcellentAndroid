package com.it.excellent.domain.proxy

import android.content.Context
import androidx.lifecycle.LiveData
import com.it.excellent.data.bean.TestAlbum
import com.kunminx.player.contract.ICacheProxy
import com.kunminx.player.contract.IPlayController
import com.kunminx.player.contract.IServiceNotifier
import com.kunminx.player.domain.MusicDTO
import com.kunminx.player.domain.PlayerController
import com.kunminx.player.domain.PlayingInfoManager

class PlayerManager private constructor() : IPlayController<TestAlbum, TestAlbum.TestMusic, TestAlbum.TestArtist> {

    companion object {
        private val S_MANAGER = PlayerManager()

        fun getInstance() = S_MANAGER

        private val mController = PlayerController<TestAlbum, TestAlbum.TestMusic, TestAlbum.TestArtist>()

        private var mIsInit: Boolean? = false
    }

    fun init(context: Context) {
        if (!mIsInit) {

        }
    }

    override fun getAlbum(): TestAlbum {
        TODO("Not yet implemented")
    }

    override fun getAlbumMusics(): MutableList<TestAlbum.TestMusic> {
        TODO("Not yet implemented")
    }

    override fun setChangingPlayingMusic(changingPlayingMusic: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getAlbumIndex(): Int {
        TODO("Not yet implemented")
    }

    override fun getRepeatMode(): Enum<PlayingInfoManager.RepeatMode> {
        TODO("Not yet implemented")
    }

    override fun getCurrentPlayingMusic(): TestAlbum.TestMusic {
        TODO("Not yet implemented")
    }

    override fun init(
        context: Context?,
        iServiceNotifier: IServiceNotifier?,
        iCacheProxy: ICacheProxy?
    ) {
        val appContext = context!!.applicationContext

//        val proxy = HttpP
    }

    override fun playAudio() {
        TODO("Not yet implemented")
    }

    override fun playAudio(albumIndex: Int) {
        TODO("Not yet implemented")
    }

    override fun playNext() {
        TODO("Not yet implemented")
    }

    override fun playPrevious() {
        TODO("Not yet implemented")
    }

    override fun playAgain() {
        TODO("Not yet implemented")
    }

    override fun togglePlay() {
        TODO("Not yet implemented")
    }

    override fun pauseAudio() {
        TODO("Not yet implemented")
    }

    override fun resumeAudio() {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun changeMode() {
        TODO("Not yet implemented")
    }

    override fun isPlaying(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPaused(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isInit(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSeek(progress: Int) {
        TODO("Not yet implemented")
    }

    override fun getTrackTime(progress: Int): String {
        TODO("Not yet implemented")
    }

    override fun getUiStates(): LiveData<MusicDTO<TestAlbum, TestAlbum.TestMusic, TestAlbum.TestArtist>> {
        TODO("Not yet implemented")
    }

    override fun loadAlbum(musicAlbum: TestAlbum?, playIndex: Int) {
        TODO("Not yet implemented")
    }

    override fun loadAlbum(musicAlbum: TestAlbum?) {
        TODO("Not yet implemented")
    }
}