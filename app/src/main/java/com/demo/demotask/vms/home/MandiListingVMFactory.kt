package com.demo.demotask.vms.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.demotask.local.repositories.MandiListingRepos
import java.lang.IllegalArgumentException

 class MandiListingVMFactory (var application:Application):ViewModelProvider.Factory {

     override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return if (modelClass.isAssignableFrom(MandiListingsVM::class.java)) {
             MandiListingsVM(application) as T
         } else {
             throw IllegalArgumentException("ViewModel Not Found")
         }
     }
}

