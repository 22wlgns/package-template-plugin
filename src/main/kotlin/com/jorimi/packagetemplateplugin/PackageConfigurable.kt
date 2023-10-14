package com.jorimi.packagetemplateplugin

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.Configurable
import com.intellij.ui.components.JBList
import java.awt.BorderLayout
import java.awt.Color
import javax.swing.*

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

    private val listModel = DefaultListModel<String>()
    private var panel: JPanel? = JPanel(BorderLayout())
    private var packageList: JBList<String> = JBList(listModel)

    private val borderColor = Color.decode("#323232")

    private val toolBar =
        ActionManager.getInstance().createActionToolbar("PackageConfigurableToolbar", DefaultActionGroup().apply {
            add(object : AnAction(AllIcons.General.Add) {
                override fun actionPerformed(e: AnActionEvent) {
                    listModel.addElement("")  // 빈 항목 추가
                    packageList.selectedIndex = listModel.size - 1  // 방금 추가한 항목 선택
                    packageList.ensureIndexIsVisible(packageList.selectedIndex)  // 항목이 보이도록 스크롤합니다
                    packageList.updateUI()  // UI를 업데이트합니다.
                }
            })
            add(object : AnAction(AllIcons.General.Remove) {
                override fun actionPerformed(e: AnActionEvent) {
                    val selectedIndex = packageList.selectedIndex
                    if (selectedIndex != -1) {
                        listModel.remove(selectedIndex)
                    }
                    packageList.updateUI()  // UI를 업데이트합니다.
                }
            })
        }, true).component

    init {
        toolBar.border = BorderFactory.createMatteBorder(1, 1, 0, 1, borderColor)
        packageList.border = BorderFactory.createLineBorder(borderColor, 1)
        packageList.cellRenderer = EditableListCellRenderer()

        val scrollPane = JScrollPane(packageList)
        scrollPane.border = BorderFactory.createEmptyBorder()

        panel?.add(scrollPane, BorderLayout.CENTER)
        panel?.add(toolBar, BorderLayout.NORTH)
    }

    private val settings: PackageConfigService
        get() = ApplicationManager.getApplication().getService(PackageConfigService::class.java)

    override fun createComponent(): JComponent? {
        return panel
    }

    override fun isModified(): Boolean {
        val currentList = mutableListOf<String>()
        for (i in 0 until packageList.model.size) {
            currentList.add(packageList.model.getElementAt(i))
        }
        return settings.packageNames != currentList
    }

    override fun apply() {
        val currentList = mutableListOf<String>()
        for (i in 0 until packageList.model.size) {
            currentList.add(packageList.model.getElementAt(i))
        }
        settings.packageNames = currentList
    }

    override fun reset() {
        val model = DefaultListModel<String>()
        settings.packageNames.forEach { model.addElement(it) }
        packageList.model = model
    }

    override fun getDisplayName(): String {
        return "패키지 템플릿"
    }

    override fun getHelpTopic(): String? {
        return null
    }

    override fun disposeUIResources() {
        panel = null
    }

}
