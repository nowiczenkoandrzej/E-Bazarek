package com.nowiczenkoandrzej.e_bazarek.utils

sealed class AccountState{
    object Success : AccountState()
    class Failure(val error: String): AccountState()
    object Loading: AccountState()
    object Empty: AccountState()
}
