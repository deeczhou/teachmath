import React from 'react';
import axios from 'axios';
import { Card } from '@material-ui/core'
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from "@material-ui/core/styles";
import TextField from '@material-ui/core/TextField';
import InputBase from '@material-ui/core/InputBase';
import IconButton from '@material-ui/core/IconButton';


export default class AdditionList extends React.Component {
  state = {
    questions: []
  }

  check(a, b) {  
    console.log(this.state.answer);
    console.log(a);
    console.log(b);
    if (a + b == this.state.answer){
      alert("correct!");
    } else {
      alert("incorrect, try again?");
    }
    
  }

  handleInput(e) {
    console.log(e.target.value);
    this.setState({answer: e.target.value});
    console.log(this.state);
  }

  componentDidMount() {
    axios.get(`http://chips4ever.duckdns.org:8989/add?from=100&to=1000&size=10`, {crossdomain:true})
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
        Additions
        {this.state.questions.map(q => 
          <Card>
            <CardContent>
              <Typography variant="h5" component="h2">
                {q.a} + {q.b} = 
              </Typography>
              <InputBase
                placeholder="What is the total?"
                inputProps={{ 'aria-label': 'What is the total?' }}
                type="text"
                onChange={(e) => this.handleInput(e)}
                />
              <Typography variant="h5" component="h2" hidden="true" id="hiddenAnswer">
                answer = {q.sum} 
              </Typography>
              <Button size="small" onClick={()=> this.check(q.a, q.b)} type="success">Submit</Button>
            </CardContent>
          </Card>
        )}
      </div>
    );
  }
}