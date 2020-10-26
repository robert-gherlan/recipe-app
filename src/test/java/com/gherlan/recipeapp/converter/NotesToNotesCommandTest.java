package com.gherlan.recipeapp.converter;

import com.gherlan.recipeapp.command.NotesCommand;
import com.gherlan.recipeapp.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotesToNotesCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "Notes";
    private NotesToNotesCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNull() {
        assertThat(converter.convert(null)).isNull();
    }

    @Test
    public void testEmptyObject() {
        assertThat(converter.convert(new Notes())).isNotNull();
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assertThat(notesCommand).isNotNull();
        assertThat(notesCommand.getId()).isEqualTo(ID_VALUE);
        assertThat(notesCommand.getRecipeNotes()).isEqualTo(RECIPE_NOTES);
    }
}

