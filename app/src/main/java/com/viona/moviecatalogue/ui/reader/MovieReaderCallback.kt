package com.viona.moviecatalogue.ui.reader

interface MovieReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}