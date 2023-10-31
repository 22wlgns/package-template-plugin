package com.jorimi.packagetemplateplugin

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

/**
 * packageName    : com.jorimi.packagetemplateplugin
 * fileName       : PackageConfigService
 * author         : Jihun Kim
 * date           : 10/13/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/13/23        Jihun Kim       최초 생성
 */
@Service
@State(
    name = "PackageSettings",
    storages = [Storage("~/.idea/myPluginSettings.xml")]
)
class PackageConfigService : PersistentStateComponent<PackageConfigService.State> {

    data class State(
        var packageNames: MutableList<String> = mutableListOf()
    )

    private var state = State()

    var packageNames: MutableList<String>
        get() = state.packageNames
        set(value) {
            state.packageNames = value
        }

    override fun getState(): State {
        return state
    }

    override fun loadState(state: State) {
        this.state = state
    }

}