package com.deividasstr.utils

import com.deividasstr.data.store.models.MyObjectBox
import com.deividasstr.domain.common.UnitTest
import io.objectbox.BoxStore
import org.junit.After
import org.junit.Before
import java.io.File
import java.io.IOException

abstract class AbstractObjectBoxTest : UnitTest() {
    private lateinit var boxStoreDir: File
    protected lateinit var store: BoxStore

    @Before
    @Throws(IOException::class)
    fun setUp() {
        // store the database in the systems temporary files folder
        val tempFile = File.createTempFile("object-store-test", "")
        // ensure file does not exist so builder creates a directory instead
        tempFile.delete()
        boxStoreDir = tempFile
        store = MyObjectBox.builder().directory(boxStoreDir).build()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        store.close()
        store.deleteAllFiles()
    }
}
