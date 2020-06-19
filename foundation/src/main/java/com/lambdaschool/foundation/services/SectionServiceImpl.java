package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sectionService")
public class SectionServiceImpl implements SectionService
{
    @Override
    public List<Section> findAll()
    {
        return null;
    }
}