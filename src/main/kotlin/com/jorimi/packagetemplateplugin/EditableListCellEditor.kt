package com.jorimi.packagetemplateplugin

import java.awt.Color
import javax.swing.BorderFactory
import javax.swing.DefaultCellEditor
import javax.swing.JTextField

/**
 * packageName    : com.jorimi.packagetemplateplugin
 * fileName       : EditableListCellEditor
 * author         : Jihun Kim
 * date           : 10/31/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/31/23        Jihun Kim       최초 생성
 */
class EditableListCellEditor : DefaultCellEditor(JTextField()) {

    private val borderColor = Color.decode("#323232")

    init {
        (component as JTextField).border = BorderFactory.createLineBorder(borderColor)
    }

    override fun getCellEditorValue(): Any {
        return (component as JTextField).text
    }

}