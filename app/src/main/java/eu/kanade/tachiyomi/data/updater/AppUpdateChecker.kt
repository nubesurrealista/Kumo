package eu.kanade.tachiyomi.data.updater

import android.content.Context
import tachiyomi.domain.release.interactor.GetApplicationRelease

class AppUpdateChecker {

    suspend fun checkForUpdate(context: Context, forceCheck: Boolean = false): GetApplicationRelease.Result {
        // Actualización deshabilitada: no se realiza ningún chequeo
        return GetApplicationRelease.Result.NoNewUpdate
    }
}