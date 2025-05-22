package polskowniaApp.course;

import polskowniaApp.course.lecture.Lecture;

interface LectureRepository
{
    Lecture save(final Lecture toSave);
}
