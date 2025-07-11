package com.agilepop.repository;

import com.agilepop.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Aqui você pode criar métodos personalizados se quiser (ex: findByNome, etc.)
}
