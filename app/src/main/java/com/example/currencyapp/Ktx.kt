package com.example.currencyapp

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.currentDestination() = findNavController().currentDestination

fun Fragment.previousDestination() = findNavController().previousBackStackEntry?.destination

fun NavDestination.getDestinationIdFromAction(@IdRes actionId: Int) =
    getAction(actionId)?.destinationId

private fun Fragment.isAlreadyAtDestination(@IdRes actionId: Int): Boolean {
    val previousDestinationId = previousDestination()?.getDestinationIdFromAction(actionId)
    val currentDestinationId = currentDestination()?.id
    return previousDestinationId == currentDestinationId
}

fun Fragment.navigate(@IdRes actionId: Int, bundle: Bundle?) {
    if (!isAlreadyAtDestination(actionId)) {
//    if (!isAlreadyAtDestination(directions.actionId)) {
        findNavController().navigate(actionId, bundle)
    }
}

fun <T : ViewModel> Fragment.obtainViewModel(
    owner: ViewModelStoreOwner,
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory
) =
    ViewModelProvider(owner, viewModelFactory)[viewModelClass]