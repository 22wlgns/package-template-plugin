package com.jorimi.packagetemplateplugin

import java.awt.Color
import javax.swing.BorderFactory
import javax.swing.table.DefaultTableCellRenderer

/**
 * packageName    : com.jorimi.packagetemplateplugin
 * fileName       : EditableListCellRenderer
 * author         : Jihun Kim
 * date           : 10/15/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/15/23        Jihun Kim       최초 생성
 */
class EditableListCellRenderer : DefaultTableCellRenderer() {

    private val borderColor = Color.decode("#323232")

    init {
        border = BorderFactory.createLineBorder(borderColor)
    }

}