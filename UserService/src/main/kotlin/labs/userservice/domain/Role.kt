package labs.userservice.domain

enum class Role {
    ROLE_USER,
    ROLE_ADMIN;

    companion object {
        fun from(value: String): Role =
            Role.entries.find { it.name.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown role: $value")
    }
}

