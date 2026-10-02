package org.polytech.spring;

public final class ActeurMapper {
    private ActeurMapper() {}

    public static ActeurDto toDto(Acteur a) {
        return new ActeurDto(a.getId(), a.getNom(), a.getPrenom());
    }
}