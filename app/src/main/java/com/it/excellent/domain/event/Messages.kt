package com.it.excellent.domain.event

data class Messages(val eventId: Int) {
    companion object {
        const val CLOSE_SLIDE_PANEL = 1
        const val CLOSE_ACTIVITY = 2
        const val OPEN_DRAWER = 3
        const val ADD_SLIDE_LISTENER = 4
        const val LOGIN_SUCCESS = 5
    }
}