package com.jorimi.packagetemplateplugin

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.Configurable
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import javax.swing.*
import javax.swing.table.DefaultTableModel

/**
 * packageName    : com.jorimi.packagetemplateplugin
 * fileName       : PackageConfigurable
 * author         : Jihun Kim
 * date           : 10/13/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/13/23        Jihun Kim       최초 생성
 */
class PackageConfigurable : Configurable {

    private val tableModel = DefaultTableModel()
    private var panel: JPanel? = JPanel(BorderLayout())
    private val packageTable = JTable(tableModel)
    private val borderColor = Color.decode("#323232")

    init {
        tableModel.addColumn("Package")

        val addAction = AddAction {
            tableModel.addRow(arrayOf("New Item"))
            packageTable.setRowSelectionInterval(tableModel.rowCount - 1, tableModel.rowCount - 1)
        }

        val removeAction = RemoveAction {
            val selectedIndex = packageTable.selectedRow
            if (selectedIndex != -1) {
                tableModel.removeRow(selectedIndex)
            }
        }

        val actionGroup = DefaultActionGroup().apply {
            add(addAction)
            add(removeAction)
        }

        val toolBar =
            ActionManager.getInstance().createActionToolbar("PackageConfigurableToolbar", actionGroup, true).component
        toolBar.border = BorderFactory.createMatteBorder(1, 1, 0, 1, borderColor)

        packageTable.border = BorderFactory.createLineBorder(borderColor, 1)
        packageTable.columnModel.getColumn(0).cellRenderer = EditableListCellRenderer()
        packageTable.columnModel.getColumn(0).cellEditor = EditableListCellEditor()

        packageTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        packageTable.tableHeader = null
        packageTable.showHorizontalLines = false
        packageTable.showVerticalLines = false

        val scrollPane = JScrollPane(packageTable)
        scrollPane.border = BorderFactory.createEmptyBorder()

        panel?.add(scrollPane, BorderLayout.CENTER)
        panel?.add(toolBar, BorderLayout.NORTH)
    }

    private val settings: PackageConfigService
        get() = ApplicationManager.getApplication().getService(PackageConfigService::class.java)

    override fun createComponent(): JComponent? {
        return panel
    }

    override fun disposeUIResources() {
        panel = null
    }

    override fun isModified(): Boolean {
        val currentList = mutableListOf<String>()
        for (i in 0 until packageTable.rowCount) {
            currentList.add(packageTable.getValueAt(i, 0).toString())
        }
        return settings.packageNames != currentList
    }

    override fun apply() {
        val currentList = mutableListOf<String>()
        for (i in 0 until packageTable.rowCount) {
            currentList.add(packageTable.getValueAt(i, 0).toString())
        }
        settings.packageNames = currentList
    }

    override fun reset() {
        while (tableModel.rowCount > 0) {
            tableModel.removeRow(0)
        }
        settings.packageNames.forEach { tableModel.addRow(arrayOf(it)) }
    }

    override fun getDisplayName(): String {
        return "패키지 템플릿"
    }

    override fun getHelpTopic(): String? {
        return null
    }

}
