package com.callatgame.frag.main.task

import android.content.Context
import com.callatgame.frag.common.ui.DataItemView
import com.callatgame.frag.core.AbstractTask
import com.callatgame.frag.model.DefaultResponse
import com.callatgame.frag.model.payload.UpdateDataPayload
import com.callatgame.frag.service.PlayerService
import com.callatgame.frag.service.UserService

class UpdateDataTask (val context: Context, val dataType : DataItemView.DataType, val field : DataItemView.Field, val newValue : String) : AbstractTask<DefaultResponse>(context){

    override fun doCall(): DefaultResponse {

        when(dataType){
            DataItemView.DataType.PLAYER -> PlayerService(context).updatePlayerData(UpdateDataPayload(field.name, newValue))
            DataItemView.DataType.USER -> UserService(context).updateUserData(UpdateDataPayload(field.name, newValue))
        }

        UserService(context).getUserData()
        return DefaultResponse("")
    }
}