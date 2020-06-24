package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Section;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service("sectionService")
public class SectionServiceImpl implements SectionService
{
    @Override
    public List<Section> findAll()
    {
        return null;
    }

    @Transactional
    @Override
    public Section save(
        Section section)
    {
        Section currentUser = userService.findUserById(userid);

        Section newSection = new Section(section);
        return sectionrepos.save(newUserEmail);
    }


}