package com.callatgame.frag.service

import android.content.Context
import com.callatgame.frag.core.AbstractService
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.Player
import com.callatgame.frag.model.payload.UpdateDataPayload

class PlayerService(context: Context) : AbstractService(context) {

    fun getPlayerData(playerId: Int): Player {
        return execute(
            apiEndPoint.getPlayerData(playerId)
        )!!
    }

    fun updatePlayerData(payload: UpdateDataPayload) : DefaultResponse? {
        return execute(
            apiEndPoint.updatePlayerData(payload)
        )
    }
}