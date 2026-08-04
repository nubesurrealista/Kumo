package eu.kanade.domain.extension.interactor

import eu.kanade.domain.source.service.SourcePreferences
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.extension.ExtensionManager
import eu.kanade.tachiyomi.util.system.LocaleHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetExtensionLanguages(
    private val preferences: SourcePreferences,
    private val extensionManager: ExtensionManager,
) {
    fun subscribe(): Flow<List<String>> {
        return combine(
            preferences.enabledLanguages.changes(),
            extensionManager.availableExtensionsFlow,
        ) { enabledLanguage, availableExtensions ->
            availableExtensions
                .flatMap { ext ->
                    ext.sources.map { it.lang }
                }
                .distinct()
                .sortedWith(
                    compareBy<String> { it !in enabledLanguage }.then(LocaleHelper.comparator),
                )
        }
    }

    companion object {
        fun getLanguageIconID(lang: String): Int? {
            return when (lang) {
                "all" -> R.drawable.eu
                "af" -> R.drawable.af
                "am" -> R.drawable.am
                "ar" -> R.drawable.ar
                "az" -> R.drawable.az
                "be" -> R.drawable.by
                "bg" -> R.drawable.bg
                "bn" -> R.drawable.bd
                "br" -> R.drawable.br
                "bs" -> R.drawable.ba
                "ca" -> R.drawable.ca
                "ceb" -> R.drawable.ph
                "cn" -> R.drawable.cn
                "co" -> R.drawable.co
                "cs" -> R.drawable.cz
                "da" -> R.drawable.dk
                "de" -> R.drawable.de
                "el" -> R.drawable.gr
                "en" -> R.drawable.us
                "es-419" -> R.drawable.mx
                "es" -> R.drawable.es
                "et" -> R.drawable.et
                "eu" -> R.drawable.es
                "fa" -> R.drawable.ir
                "fi" -> R.drawable.fi
                "fil" -> R.drawable.ph
                "fo" -> R.drawable.fo
                "fr" -> R.drawable.fr
                "ga" -> R.drawable.ie
                "gn" -> R.drawable.py
                "gu" -> R.drawable.in_
                "ha" -> R.drawable.ng
                "he" -> R.drawable.il
                "hi" -> R.drawable.in_
                "hr" -> R.drawable.hr
                "ht" -> R.drawable.ht
                "hu" -> R.drawable.hu
                "hy" -> R.drawable.am
                "id" -> R.drawable.id
                "ig" -> R.drawable.ng
                "is" -> R.drawable.is_
                "it" -> R.drawable.it
                "ja" -> R.drawable.jp
                "jv" -> R.drawable.id
                "ka" -> R.drawable.ge
                "kk" -> R.drawable.kz
                "km" -> R.drawable.kh
                "kn" -> R.drawable.in_
                "ko" -> R.drawable.kr
                "kr" -> R.drawable.ng
                "ku" -> R.drawable.iq
                "ky" -> R.drawable.kg
                "lb" -> R.drawable.lu
                "lmo" -> R.drawable.it
                "lo" -> R.drawable.la
                "lt" -> R.drawable.lt
                "lv" -> R.drawable.lv
                "mg" -> R.drawable.mg
                "mi" -> R.drawable.nz
                "mk" -> R.drawable.mk
                "ml" -> R.drawable.in_
                "mn" -> R.drawable.mn
                "mo" -> R.drawable.md
                "mr" -> R.drawable.in_
                "ms" -> R.drawable.my
                "mt" -> R.drawable.mt
                "my" -> R.drawable.mm
                "ne" -> R.drawable.np
                "nl" -> R.drawable.nl
                "no" -> R.drawable.no
                "ny" -> R.drawable.mw
                "pl" -> R.drawable.pl
                "ps" -> R.drawable.af
                "pt-BR" -> R.drawable.br
                "pt-PT" -> R.drawable.pt
                "pt" -> R.drawable.pt
                "rm" -> R.drawable.ch
                "ro" -> R.drawable.ro
                "ru" -> R.drawable.ru
                "sd" -> R.drawable.sd
                "sh" -> R.drawable.hr
                "si" -> R.drawable.lk
                "sk" -> R.drawable.sk
                "sl" -> R.drawable.si
                "sm" -> R.drawable.ws
                "sn" -> R.drawable.zw
                "so" -> R.drawable.so
                "sq" -> R.drawable.al
                "sr" -> R.drawable.rs
                "st" -> R.drawable.ls
                "sv" -> R.drawable.se
                "sw" -> R.drawable.tz
                "ta" -> R.drawable.in_
                "te" -> R.drawable.in_
                "tg" -> R.drawable.tj
                "th" -> R.drawable.th
                "ti" -> R.drawable.er
                "tk" -> R.drawable.tm
                "tl" -> R.drawable.ph
                "to" -> R.drawable.to
                "tr" -> R.drawable.tr
                "uk" -> R.drawable.ua
                "ur" -> R.drawable.pk
                "uz" -> R.drawable.uz
                "vec" -> R.drawable.it
                "vi" -> R.drawable.vn
                "yo" -> R.drawable.ng
                "zh-Hans" -> R.drawable.cn
                "zh-Hant" -> R.drawable.tw
                "zh" -> R.drawable.cn
                "zu" -> R.drawable.za
                "gl" -> R.drawable.gl
                "in" -> R.drawable.id
                "nb-NO" -> R.drawable.no
                "nn" -> R.drawable.no
                "sc" -> R.drawable.sc
                "sdh" -> R.drawable.ir
                "sah" -> R.drawable.ru
                "cv" -> R.drawable.cv
                "sa" -> R.drawable.sa
                "ka-GE" -> R.drawable.ge
                "zh-CN" -> R.drawable.cn
                "zh-TW" -> R.drawable.tw
                else -> null
            }
        }
    }
}