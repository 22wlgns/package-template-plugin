package com.jorimi.packagetemplateplugin

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * packageName    : com.jorimi.packagetemplateplugin
 * fileName       : RemoveAction
 * author         : Jihun Kim
 * date           : 10/15/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/15/23        Jihun Kim       최초 생성
 */
class RemoveAction(private val action: () -> Unit) : AnAction(AllIcons.General.Remove) {

    override fun actionPerformed(e: AnActionEvent) {
        action.invoke()
    }

}