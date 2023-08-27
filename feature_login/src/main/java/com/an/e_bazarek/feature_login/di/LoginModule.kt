package com.an.e_bazarek.feature_login.di

import android.content.Context
import com.an.e_bazarek.feature_login.data.google_auth_client.GoogleAuthUIClient
import com.an.e_bazarek.feature_login.data.repository.LoginRepositoryImpl
import com.an.e_bazarek.feature_login.domain.repository.LoginRepository
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideIdentity(
        @ApplicationContext context: Context
    ): SignInClient = Identity.getSignInClient(context)

    @Provides
    @Singleton
    fun provideGoogleAuthClient(
        @ApplicationContext context: Context,
        oneTapClient: SignInClient,
        auth: FirebaseAuth
    ): GoogleAuthUIClient = GoogleAuthUIClient(
        context = context,
        oneTapClient = oneTapClient,
        auth = auth
    )

}

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindLoginRepository(
        repository: LoginRepositoryImpl
    ): LoginRepository
    
}