package com.jorimi.packagetemplateplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiManager

/**
 * packageName    : com.jorimi.packagetemplateplugin
 * fileName       : PackageGroupAction
 * author         : Jihun Kim
 * date           : 10/13/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/13/23        Jihun Kim       최초 생성
 */
class PackageGroupAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val directory = getCurrentDirectory(e) ?: return

        val packageName = Messages.showInputDialog(project, "패키지명을 입력해 주세요.", "패키지명 입력", null)
        if (!packageName.isNullOrBlank()) {
            val targetDirectory = createPackage(directory, packageName, project)
            createDefaultSubPackages(targetDirectory, project)
        }
    }

    private fun getCurrentDirectory(e: AnActionEvent): PsiDirectory? {
        val virtualFile = e.getData(com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE)
        val project = e.project ?: return null
        return PsiManager.getInstance(project).findDirectory(virtualFile!!)
    }

    private fun createPackage(directory: PsiDirectory, packageName: String, project: Project): PsiDirectory {
        var targetDirectory = directory
        WriteCommandAction.runWriteCommandAction(project) {
            val parts = packageName.split(".")
            for (part in parts) {
                targetDirectory = targetDirectory.createSubdirectory(part)
            }
        }
        return targetDirectory
    }

    private fun createDefaultSubPackages(directory: PsiDirectory, project: Project) {

        val settings = ServiceManager.getService(PackageConfigService::class.java)
        val userDefinedSubPackages = settings.packageNames

        WriteCommandAction.runWriteCommandAction(project) {
            for (subPackage in userDefinedSubPackages) {
                directory.createSubdirectory(subPackage)
            }
        }
    }

}