import React from 'react';
import axios from 'axios';

export default class AdditionList extends React.Component {
  state = {
    questions: []
  }

  componentDidMount() {
    axios.get(`http://localhost:8989/add?from=100&to=1000`, {crossdomain:true})
      .then(res => {
        const questions = res.data.questions;
        this.setState({ questions });
        console.log(questions);
      }).catch(err => {
          console.log(err);
      })
  }

  render() {
    return (
      <ul>
        Questions:
        {this.state.questions.map(q => <li>{q.a} {q.b} {q.sum}</li>)}
      </ul>
    )
  }
}