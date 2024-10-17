package org.febrinomahardiva.mobileprogrammingcompose2.ui.screen.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.febrinomahardiva.mobpro2s.util.SharedUtil

class AppViewModel : ViewModel() {
    val userFlow = SharedUtil.getUserFlow(viewModelScope)
}