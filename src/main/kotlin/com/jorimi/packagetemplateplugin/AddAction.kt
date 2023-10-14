package com.jorimi.packagetemplateplugin

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * packageName    : com.jorimi.packagetemplateplugin
 * fileName       : AddAction
 * author         : Jihun Kim
 * date           : 10/15/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/15/23        Jihun Kim       최초 생성
 */
class AddAction(private val action: () -> Unit) : AnAction(AllIcons.General.Add) {

    override fun actionPerformed(e: AnActionEvent) {
        action.invoke()
    }

}