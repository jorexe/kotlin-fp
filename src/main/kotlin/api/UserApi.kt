package api

import models.User
import models.UserId
import java.util.Optional

interface UserApi {

    fun findById(id: UserId): Optional<User>

}
