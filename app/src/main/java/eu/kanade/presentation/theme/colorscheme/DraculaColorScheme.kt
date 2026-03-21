package eu.kanade.presentation.theme.colorscheme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal object DraculaColorScheme : BaseColorScheme() {

    override val darkScheme = darkColorScheme(
        primary = Color(0xFFBD93F9),
        onPrimary = Color(0xFF282A36),
        primaryContainer = Color(0xFFBD93F9),
        onPrimaryContainer = Color(0xFF282A36),
        secondary = Color(0xFFBD93F9),
        onSecondary = Color(0xFF282A36),
        secondaryContainer = Color(0xFF44475A),
        onSecondaryContainer = Color(0xFFBD93F9),
        tertiary = Color(0xFFBD93F9),
        onTertiary = Color(0xFF282A36),
        tertiaryContainer = Color(0xFF44475A),
        onTertiaryContainer = Color(0xFFF8F8F2),
        error = Color(0xFFFF5555),
        onError = Color(0xFF282A36),
        errorContainer = Color(0xFFFF5555),
        onErrorContainer = Color(0xFFF8F8F2),
        background = Color(0xFF282A36),
        onBackground = Color(0xFFF8F8F2),
        surface = Color(0xFF282A36),
        onSurface = Color(0xFFF8F8F2),
        surfaceVariant = Color(0xFF44475A),
        onSurfaceVariant = Color(0xFFF8F8F2),
        outline = Color(0xFFBD93F9),
        outlineVariant = Color(0xFF6272A4),
        scrim = Color(0xFF282A36),
        inverseSurface = Color(0xFFF8F8F2),
        inverseOnSurface = Color(0xFF44475A),
        inversePrimary = Color(0xFFBD93F9),
        surfaceDim = Color(0xFF282A36),
        surfaceBright = Color(0xFF44475A),
        surfaceContainerLowest = Color(0xFF282A36),
        surfaceContainerLow = Color(0xFF44475A),
        surfaceContainer = Color(0xFF44475A),
        surfaceContainerHigh = Color(0xFF44475A),
        surfaceContainerHighest = Color(0xFF44475A)
    )

    override val lightScheme = lightColorScheme(
        primary = Color(0xFFBD93F9),
        onPrimary = Color(0xFFF8F8F2),
        primaryContainer = Color(0xFFBD93F9),
        onPrimaryContainer = Color(0xFFF8F8F2),
        secondary = Color(0xFFBD93F9),
        onSecondary = Color(0xFFF8F8F2),
        secondaryContainer = Color(0xFFF8F8F2),
        onSecondaryContainer = Color(0xFFBD93F9),
        tertiary = Color(0xFFBD93F9),
        onTertiary = Color(0xFFF8F8F2),
        tertiaryContainer = Color(0xFFF8F8F2),
        onTertiaryContainer = Color(0xFF44475A),
        error = Color(0xFFFF5555),
        onError = Color(0xFFF8F8F2),
        errorContainer = Color(0xFFFF5555),
        onErrorContainer = Color(0xFFF8F8F2),
        background = Color(0xFFF8F8F2),
        onBackground = Color(0xFF44475A),
        surface = Color(0xFFF8F8F2),
        onSurface = Color(0xFF44475A),
        surfaceVariant = Color(0xFFF8F8F2),
        onSurfaceVariant = Color(0xFF44475A),
        outline = Color(0xFFBD93F9),
        outlineVariant = Color(0xFF6272A4),
        scrim = Color(0xFFF8F8F2),
        inverseSurface = Color(0xFF44475A),
        inverseOnSurface = Color(0xFFF8F8F2),
        inversePrimary = Color(0xFFBD93F9),
        surfaceDim = Color(0xFFF8F8F2),
        surfaceBright = Color(0xFFF8F8F2),
        surfaceContainerLowest = Color(0xFFF8F8F2),
        surfaceContainerLow = Color(0xFFF8F8F2),
        surfaceContainer = Color(0xFFF8F8F2),
        surfaceContainerHigh = Color(0xFFF8F8F2),
        surfaceContainerHighest = Color(0xFFF8F8F2)
    )
}