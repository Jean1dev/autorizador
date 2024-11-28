package org.autorizador.core.entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<AccountEntitie, String> {
}
