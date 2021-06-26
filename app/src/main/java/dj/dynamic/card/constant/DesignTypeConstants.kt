package dj.dynamic.card.constant

/**
 * Singleton object which contains the list of possible values for [dj.dynamic.card.model.api.Card_groups.design_type]
 */
object DesignTypeConstants {
    const val SMALL_DISPLAY_CARD_HC1 = "HC1"
    const val BIG_DISPLAY_CARD_HC3 = "HC3"
    const val IMAGE_CARD_HC5 = "HC5"
    const val SMALL_CARD_WITH_ARROW_HC6 = "HC6"
    const val DYNAMIC_WIDTH_CARD_HC9 = "HC9"

    // Please add any new supported card design type constant above this line.
    /**
     * Unknown card is a constant ONLY for error handling, used when none of the above matches.
     */
    const val UNKNOWN_CARD = "UNKNOWN_CARD"
}