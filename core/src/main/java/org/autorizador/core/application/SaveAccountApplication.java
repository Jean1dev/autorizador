package org.autorizador.core.application;

import org.autorizador.core.entities.AccountEntitie;
import org.autorizador.core.entities.AccountRepo;
import org.autorizador.core.utils.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaveAccountApplication {
    private final AccountRepo repo;

    public SaveAccountApplication(AccountRepo repo) {
        this.repo = repo;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public String saveAccount(boolean activeCard, double availableLimit) {
        var id = IdUtils.uuid();
        repo.save(new AccountEntitie(id, activeCard, availableLimit));
        return id;
    }
}
