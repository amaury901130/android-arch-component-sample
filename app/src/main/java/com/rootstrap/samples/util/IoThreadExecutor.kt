package com.rootstrap.samples.util

/**
 * @author Amaury Ricardo Miranda 2019
 * */

import java.util.concurrent.Executor
import java.util.concurrent.Executors

var ioThread: Executor = IoThreadExecutor()

class IoThreadExecutor : Executor {

    private val mDiskIO: Executor

    init {
        mDiskIO = Executors.newSingleThreadExecutor()
    }

    override fun execute(command: Runnable) {
        mDiskIO.execute(command)
    }
}
