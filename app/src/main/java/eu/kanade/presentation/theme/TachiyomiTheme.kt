package eu.kanade.presentation.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import eu.kanade.domain.ui.UiPreferences
import eu.kanade.domain.ui.model.AppTheme
import eu.kanade.presentation.theme.colorscheme.BaseColorScheme
import eu.kanade.presentation.theme.colorscheme.CatppuccinColorScheme
import eu.kanade.presentation.theme.colorscheme.DraculaColorScheme
import eu.kanade.presentation.theme.colorscheme.ObsidianColorScheme
import eu.kanade.presentation.theme.colorscheme.GreenAppleColorScheme
import eu.kanade.presentation.theme.colorscheme.LavenderColorScheme
import eu.kanade.presentation.theme.colorscheme.MidnightDuskColorScheme
import eu.kanade.presentation.theme.colorscheme.MonetColorScheme
import eu.kanade.presentation.theme.colorscheme.MonochromeColorScheme
import eu.kanade.presentation.theme.colorscheme.NordColorScheme
import eu.kanade.presentation.theme.colorscheme.StrawberryColorScheme
import eu.kanade.presentation.theme.colorscheme.TachiyomiColorScheme
import eu.kanade.presentation.theme.colorscheme.TakoColorScheme
import eu.kanade.presentation.theme.colorscheme.TealTurqoiseColorScheme
import eu.kanade.presentation.theme.colorscheme.TidalWaveColorScheme
import eu.kanade.presentation.theme.colorscheme.YinYangColorScheme
import eu.kanade.presentation.theme.colorscheme.YotsubaColorScheme
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

@Composable
fun TachiyomiTheme(
    appTheme: AppTheme? = null,
    amoled: Boolean? = null,
    content: @Composable () -> Unit,
) {
    val uiPreferences = Injekt.get<UiPreferences>()
    BaseTachiyomiTheme(
        appTheme = appTheme ?: uiPreferences.appTheme().get(),
        isAmoled = amoled ?: uiPreferences.themeDarkAmoled().get(),
        fontSize = uiPreferences.fontSize().get(),
        content = content,
    )
}

@Composable
fun TachiyomiPreviewTheme(
    appTheme: AppTheme = AppTheme.DEFAULT,
    isAmoled: Boolean = false,
    fontSize: Float = 1f,
    content: @Composable () -> Unit,
) = BaseTachiyomiTheme(appTheme, isAmoled, fontSize, content)

@Composable
private fun BaseTachiyomiTheme(
    appTheme: AppTheme,
    isAmoled: Boolean,
    fontSize: Float,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()
    MaterialExpressiveTheme(
        colorScheme = remember(appTheme, isDark, isAmoled) {
            getThemeColorScheme(
                context = context,
                appTheme = appTheme,
                isDark = isDark,
                isAmoled = isAmoled,
            )
        },
        typography = getScaledTypography(fontSize),
        content = content,
    )
}

private fun getThemeColorScheme(
    context: Context,
    appTheme: AppTheme,
    isDark: Boolean,
    isAmoled: Boolean,
): ColorScheme {
    val colorScheme = if (appTheme == AppTheme.MONET) {
        MonetColorScheme(context)
    } else {
        colorSchemes.getOrDefault(appTheme, TachiyomiColorScheme)
    }
    return colorScheme.getColorScheme(
        isDark = isDark,
        isAmoled = isAmoled,
        overrideDarkSurfaceContainers = appTheme != AppTheme.MONET,
    )
}

@Composable
@ReadOnlyComposable
private fun getScaledTypography(fontSize: Float): Typography {
    val typography = MaterialTheme.typography

    return typography.copy(
        displaySmall = typography.displaySmall.copy(fontSize = typography.displaySmall.fontSize * fontSize),
        displayMedium = typography.displayMedium.copy(fontSize = typography.displayMedium.fontSize * fontSize),
        displayLarge = typography.displayLarge.copy(fontSize = typography.displayLarge.fontSize * fontSize),
        headlineSmall = typography.headlineSmall.copy(fontSize = typography.headlineSmall.fontSize * fontSize),
        headlineMedium = typography.headlineMedium.copy(fontSize = typography.headlineMedium.fontSize * fontSize),
        headlineLarge = typography.headlineLarge.copy(fontSize = typography.headlineLarge.fontSize * fontSize),
        titleSmall = typography.titleSmall.copy(fontSize = typography.titleSmall.fontSize * fontSize),
        titleMedium = typography.titleMedium.copy(fontSize = typography.titleMedium.fontSize * fontSize),
        titleLarge = typography.titleLarge.copy(fontSize = typography.titleLarge.fontSize * fontSize),
        bodySmall = typography.bodySmall.copy(fontSize = typography.bodySmall.fontSize * fontSize),
        bodyMedium = typography.bodyMedium.copy(fontSize = typography.bodyMedium.fontSize * fontSize),
        bodyLarge = typography.bodyLarge.copy(fontSize = typography.bodyLarge.fontSize * fontSize),
        labelSmall = typography.labelSmall.copy(fontSize = typography.labelSmall.fontSize * fontSize),
        labelMedium = typography.labelMedium.copy(fontSize = typography.labelMedium.fontSize * fontSize),
        labelLarge = typography.labelLarge.copy(fontSize = typography.labelLarge.fontSize * fontSize),
    )
}

private val colorSchemes: Map<AppTheme, BaseColorScheme> = mapOf(
    AppTheme.DEFAULT to TachiyomiColorScheme,
    AppTheme.CATPPUCCIN to CatppuccinColorScheme,
    AppTheme.DRACULA to DraculaColorScheme,
    AppTheme.OBSIDIAN to ObsidianColorScheme,
    AppTheme.GREEN_APPLE to GreenAppleColorScheme,
    AppTheme.LAVENDER to LavenderColorScheme,
    AppTheme.MIDNIGHT_DUSK to MidnightDuskColorScheme,
    AppTheme.MONOCHROME to MonochromeColorScheme,
    AppTheme.NORD to NordColorScheme,
    AppTheme.STRAWBERRY_DAIQUIRI to StrawberryColorScheme,
    AppTheme.TAKO to TakoColorScheme,
    AppTheme.TEALTURQUOISE to TealTurqoiseColorScheme,
    AppTheme.TIDAL_WAVE to TidalWaveColorScheme,
    AppTheme.YINYANG to YinYangColorScheme,
    AppTheme.YOTSUBA to YotsubaColorScheme,
)