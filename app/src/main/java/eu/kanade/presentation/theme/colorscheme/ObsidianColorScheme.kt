package eu.kanade.presentation.theme.colorscheme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal object ObsidianColorScheme : BaseColorScheme() {

    override val darkScheme = darkColorScheme(
        primary = Color(0xFFA4A4A7),
        onPrimary = Color(0xFF1C1C1E),
        primaryContainer = Color(0xFF444446),
        onPrimaryContainer = Color(0xFFD5D5D6),
        secondary = Color(0xFFA4A4A7),
        onSecondary = Color(0xFF1C1C1E),
        secondaryContainer = Color(0xFF2A2A2C),
        onSecondaryContainer = Color(0xFFD5D5D6),
        tertiary = Color(0xFFA4A4A7),
        onTertiary = Color(0xFF1C1C1E),
        tertiaryContainer = Color(0xFF252527),
        onTertiaryContainer = Color(0xFFD5D5D6),
        error = Color(0xFFA4A4A7),
        onError = Color(0xFF1C1C1E),
        errorContainer = Color(0xFF444446),
        onErrorContainer = Color(0xFFD5D5D6),
        background = Color(0xFF1C1C1E),
        onBackground = Color(0xFFD5D5D6),
        surface = Color(0xFF1C1C1E),
        onSurface = Color(0xFFD5D5D6),
        surfaceVariant = Color(0xFF252527),
        onSurfaceVariant = Color(0xFFA4A4A7),
        outline = Color(0xFF5F5F61),
        outlineVariant = Color(0xFF444446),
        scrim = Color(0xBF1C1C1E),
        inverseSurface = Color(0xFFD5D5D6),
        inverseOnSurface = Color(0xFF252527),
        inversePrimary = Color(0xFF5F5F61),
        surfaceDim = Color(0xFF1C1C1E),
        surfaceBright = Color(0xFF2A2A2C),
        surfaceContainerLowest = Color(0xFF1C1C1E),
        surfaceContainerLow = Color(0xFF252527),
        surfaceContainer = Color(0xFF252527),
        surfaceContainerHigh = Color(0xFF2A2A2C),
        surfaceContainerHighest = Color(0xFF444446)
    )

    override val lightScheme = lightColorScheme(
        primary = Color(0xFF5F5F61),
        onPrimary = Color(0xFFD5D5D6),
        primaryContainer = Color(0xFFA4A4A7),
        onPrimaryContainer = Color(0xFF1C1C1E),
        secondary = Color(0xFF5F5F61),
        onSecondary = Color(0xFFD5D5D6),
        secondaryContainer = Color(0xFFD5D5D6),
        onSecondaryContainer = Color(0xFF1C1C1E),
        tertiary = Color(0xFF5F5F61),
        onTertiary = Color(0xFFD5D5D6),
        tertiaryContainer = Color(0xFFD5D5D6),
        onTertiaryContainer = Color(0xFF252527),
        error = Color(0xFF5F5F61),
        onError = Color(0xFFD5D5D6),
        errorContainer = Color(0xFFA4A4A7),
        onErrorContainer = Color(0xFF1C1C1E),
        background = Color(0xFFD5D5D6),
        onBackground = Color(0xFF1C1C1E),
        surface = Color(0xFFD5D5D6),
        onSurface = Color(0xFF1C1C1E),
        surfaceVariant = Color(0xFFD5D5D6),
        onSurfaceVariant = Color(0xFF5F5F61),
        outline = Color(0xFFA4A4A7),
        outlineVariant = Color(0xFF444446),
        scrim = Color(0x1AD5D5D6),
        inverseSurface = Color(0xFF252527),
        inverseOnSurface = Color(0xFFD5D5D6),
        inversePrimary = Color(0xFFA4A4A7),
        surfaceDim = Color(0xFFD5D5D6),
        surfaceBright = Color(0xFFD5D5D6),
        surfaceContainerLowest = Color(0xFFD5D5D6),
        surfaceContainerLow = Color(0xFFD5D5D6),
        surfaceContainer = Color(0xFFD5D5D6),
        surfaceContainerHigh = Color(0xFFD5D5D6),
        surfaceContainerHighest = Color(0xFFA4A4A7)
    )
}