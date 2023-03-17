
import './App.css';
import Courses from './Courses';
import Signup from './Signup';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link
} from "react-router-dom";

function App() {
  return (
    <div className="App">
        <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/signup">Signup</Link>
            </li>
            <li>
              <Link to="/courses">Courses</Link>
            </li>
          </ul>
        </nav>

        {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
        <Routes>
          <Route path="/signup" element={<Signup/>}>
          </Route>
          <Route path="/courses" element={<Courses/>}>
          </Route>
        </Routes>
      </div>
    </Router>
    </div>
  );
}

export default App;
