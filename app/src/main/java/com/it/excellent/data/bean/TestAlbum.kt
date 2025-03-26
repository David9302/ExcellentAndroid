package com.it.excellent.data.bean

import com.kunminx.player.bean.base.BaseAlbumItem
import com.kunminx.player.bean.base.BaseArtistItem
import com.kunminx.player.bean.base.BaseMusicItem

class TestAlbum(albumId: String?,
                title: String?, summary: String?, artist: TestArtist, coverImg: String?,
                musics: MutableList<TestMusic>?
) : BaseAlbumItem<TestAlbum.TestMusic, TestAlbum.TestArtist>(albumId, title, summary, artist, coverImg, musics
) {
    var albumMid:String? = null

    class TestMusic(musicId: String?, coverImg: String?, url: String?, title: String?,
                    artist: TestArtist?
    ) : BaseMusicItem<TestArtist>(musicId,
        coverImg, url, title, artist
    ) {
        var songMid:String? = null

    }

    class TestArtist(name: String?) : BaseArtistItem(name) {
        var birthday: String? = null
    }
}
