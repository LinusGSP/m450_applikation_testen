import React from "react";

import './Styling/Home.css';
import {Link} from "react-router-dom";
import LearnSetInfo from "./LearnSetInfo";

export default class Admin extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            learnsets: [],
            admin: false,
            password: ""
        };
    }

    componentDidMount() {
        // fetch all learnset data
        fetch("http://localhost:8080/api/learnset")
            .then(response => response.json())
            .then(jsonData => {
                this.setState({ learnsets: jsonData });
                console.log(this.state);
            });
    }

    handleSubmit = (event) => {
        event.preventDefault();
        if (this.state.password === "12345") {
            this.setState({ admin: true });
        }
    };

    handleChange = (event) => {
        this.setState({ password: event.target.value });
    };

    render() {
        // map over every learnset
        let display_sets = this.state.learnsets.map(e => {
            return (
                <LearnSetInfo key={e.id} learnSet={e} classNames="card hover-size" />
            );
        });

        let content = this.state.admin ?
                <>
                    <p className="center">Wähle ein Lernset aus oder <Link to="/create">erstelle</Link> dein eigenes</p>
                    <div className="grid-align">
                        {display_sets}
                    </div>
                </>
            :
            <form onSubmit={this.handleSubmit}>
                    <label>
                        Password:
                        <input id="password" type="password" value={this.state.password} onChange={this.handleChange} />
                    </label>
                    <button type="submit">Submit</button>
                </form>

        return (
            <div className="content">
                <div className="center title">
                    <h1>Willkommen bei der Admin Page von QuizMe</h1>
                </div>
                {content}
            </div>
        );
    }
}
