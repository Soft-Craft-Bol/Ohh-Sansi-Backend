package com.softcraft.ohhsansibackend.tutor.domain.repository.abstraction;

import com.softcraft.ohhsansibackend.tutor.domain.models.TutorArea;

public interface TutorAreaRepository {
    TutorArea save(TutorArea tutorArea);
}