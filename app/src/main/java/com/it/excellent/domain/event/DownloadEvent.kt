package com.it.excellent.domain.event

import com.it.excellent.data.bean.DownloadState

data class DownloadEvent(
    val _eventId: Int,
    val _downloadState: DownloadState? = DownloadState()
) {

    companion object {
        const val EVENT_DOWNLOAD = 1
        const val EVENT_DOWNLOAD_GLOBAL = 2
    }


    fun copy(downloadState: DownloadState):DownloadEvent {
        return DownloadEvent(_eventId, downloadState)
    }
}