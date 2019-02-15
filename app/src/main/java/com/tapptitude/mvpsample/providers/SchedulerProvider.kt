package com.tapptitude.mvpsample.providers

import io.reactivex.Scheduler

/**
 * @author Radu Dorin
 */
interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun mainThread(): Scheduler
}