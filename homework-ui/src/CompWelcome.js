import React from 'react';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  root: {
    width: '60%',
    textAlign: "center",
    margin:"auto",
  },
});

export default function CompWelcome() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <Typography variant="h2" component="h2" gutterBottom>
        Welcome Lads and Gents!
      </Typography>
      <Typography variant="body1" gutterBottom>
        Another day, another beginning, another challenge! <br></br>
        Remember! Practice makes perfect! <br></br>
        Giant leaps are made of baby steps~~ 
      </Typography>
      <Typography variant="body1" gutterBottom>
        To start, pick from the menu on the left. 
      </Typography>
    </div>
  );
}
