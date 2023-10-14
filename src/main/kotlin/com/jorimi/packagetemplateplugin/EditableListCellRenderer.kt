package com.jorimi.packagetemplateplugin

import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JList
import javax.swing.JTextField

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
class EditableListCellRenderer : DefaultListCellRenderer() {
    
    private val textField = JTextField()

    override fun getListCellRendererComponent(
        list: JList<*>,
        value: Any?,
        index: Int,
        isSelected: Boolean,
        cellHasFocus: Boolean
    ): Component {
        val renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
        if (isSelected) {
            textField.text = value?.toString() ?: ""
            return textField
        }
        return renderer
    }
    
}