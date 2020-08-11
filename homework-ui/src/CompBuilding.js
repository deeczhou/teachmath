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

export default function CompBuilding() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <Typography variant="h2" component="h2" gutterBottom>
        Stay Tuned!
      </Typography>
      <Typography variant="body1" gutterBottom>
        Coming soon...
      </Typography>
    </div>
  );
}
