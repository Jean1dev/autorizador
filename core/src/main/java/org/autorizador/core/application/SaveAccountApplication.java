package org.autorizador.core.application;

import jakarta.transaction.Transactional;
import org.autorizador.core.entities.AccountEntitie;
import org.autorizador.core.entities.AccountRepo;
import org.autorizador.core.utils.IdUtils;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SaveAccountApplication {
    private final AccountRepo repo;

    public SaveAccountApplication(AccountRepo repo) {
        this.repo = repo;
    }

    public String saveAccount(boolean activeCard, double availableLimit) {
        var id = IdUtils.uuid();
        repo.save(new AccountEntitie(id, activeCard, availableLimit));
        return id;
    }
}
