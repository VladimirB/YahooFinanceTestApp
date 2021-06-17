package com.yahoo.finance.test.domain.interactor

import com.yahoo.finance.test.domain.scheduler.SchedulerProvider

abstract class Interactor(protected val schedulerProvider: SchedulerProvider)