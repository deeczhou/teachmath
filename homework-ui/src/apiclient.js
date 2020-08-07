import React from 'react';
import axios from 'axios';
import { Card } from '@material-ui/core'
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from "@material-ui/core/styles";
import InputBase from '@material-ui/core/InputBase';
import IconButton from '@material-ui/core/IconButton';

export default class AdditionList extends React.Component {
  state = {
    questions: [],
    correct: 0,
    wrong: 0
  }

  useStyles = makeStyles((theme) => ({
    input: {
      marginLeft: theme.spacing(1),
      flex: 1,
      paddingLeft: 15,
      paddingRight: 15,
    },
    iconButton: {
      padding: 10,
    }
  }));
  
  check(a, b) {  
    console.log(this.state.answer);
    console.log(a);
    console.log(b);
    if (a + b == this.state.answer){
      let c = this.state.correct;
      c++;
      this.setState({correct: c})
    } else {
      let w = this.state.wrong;
      w++;
      this.setState({wrong: w});
    }
  }

  handleInput(e) {
    console.log(e.target.value);
    this.setState({answer: e.target.value});
    console.log(this.state);
  }

  componentDidMount() {
    axios.get(`http://chips4ever.duckdns.org:8989/add?from=100&to=1000&size=20`)
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
      <div>
        <h1>Additions</h1>
        <p>Correct Answers: {this.state.correct}</p>
        <p>Wrong Answers: {this.state.wrong}</p>
        {this.state.questions.map(q => 
          <Card>
            <CardContent>
              <Typography variant="h5" component="h2">
                {q.a} + {q.b} =    
                <InputBase className="Add-Inputbox"
                  placeholder="Equals?"
                  inputProps={{ 'aria-label': 'What is the total?' }}
                  type="text"
                  onChange={(e) => this.handleInput(e)}
                />
                <IconButton size="small" onClick={()=> this.check(q.a, q.b)} type="success">Submit</IconButton>
              </Typography>
              
              <Typography variant="h5" component="h2" hidden="true" id="hiddenAnswer">
                answer = {q.sum} 
              </Typography>
            </CardContent>
          </Card>
        )}
      </div>
    );
  }
}