import { useState, useEffect } from 'react';

export default function Courses(){
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/courses/all")
            .then(res => res.json())
            .then((result) => {
                setIsLoaded(true);
                setItems(result);
            },
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                })
    }, [])

    if (error) {
        return <> <div>Error: {error.message}</div> </>
    } else if (!isLoaded) {
        return <> <div>Loading...</div> </>
    } else {
        return (
            <>
            <div className="Courses" id="courses-div">
                {items.map(course => {
                    return (<div id="course-description">
                        <p>Course name: {course.name}</p>
                        <p>
                            Begin date: {course.beginDate}
                        </p>
                        <p>End date: {course.endDate}</p>
                    </div>)

                })}
            </div>
            </>
        )
    }
}