package com.e.rezende.notas.feature_note.domain.util


sealed class NoteOder(val oderType: OrderType) {
    class Title(orderType: OrderType): NoteOder(orderType)
    class Date(orderType: OrderType): NoteOder(orderType)
    class Color(orderType: OrderType): NoteOder(orderType)

    fun copy(orderType: OrderType): NoteOder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Title(orderType)
            is Color -> Title(orderType)
        }
    }
}