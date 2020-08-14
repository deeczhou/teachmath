import React from 'react';
import clsx from 'clsx';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import App from './App';
import ReactDOM from 'react-dom';
import Home from './Home';
import Building from './UnderBuild';
import Subtraction from './Subtraction';
import FillSubtraction from './FillInSubtraction';
import FillAdd from './FillinAdd';
import Multiply from './Multiplication';
import Division from './Division';

const drawerWidth = 150;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  appBar: {
    transition: theme.transitions.create(['margin', 'width'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    width: `calc(100% - ${drawerWidth}px)`,
    marginLeft: drawerWidth,
    transition: theme.transitions.create(['margin', 'width'], {
      easing: theme.transitions.easing.easeOut,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  hide: {
    display: 'none',
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
  },
  drawerPaper: {
    width: drawerWidth,
  },
  drawerHeader: {
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(0, 0),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
    justifyContent: 'flex-end',
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(1),
    transition: theme.transitions.create('margin', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    marginLeft: -drawerWidth,
  },
  contentShift: {
    transition: theme.transitions.create('margin', {
      easing: theme.transitions.easing.easeOut,
      duration: theme.transitions.duration.enteringScreen,
    }),
    marginLeft: 0,
  },
}));

export default function PersistentDrawerLeft() {
  const classes = useStyles();
  const theme = useTheme();
  const [open, setOpen] = React.useState(false);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  const handleAddition = () => {
    ReactDOM.render(
        <React.StrictMode>
            <App />
        </React.StrictMode>,
        document.getElementById('root')
      );
  }

  const handleHome = () => {
    ReactDOM.render(
        <React.StrictMode>
            <Home />
        </React.StrictMode>,
        document.getElementById('root')
      );
  }

  const handleSubtraction = () => {
    ReactDOM.render(
      <React.StrictMode>
          <Subtraction />
      </React.StrictMode>,
      document.getElementById('root')
    );
  }

  const handleFillSubtraction = () => {
    ReactDOM.render(
      <React.StrictMode>
          <FillSubtraction />
      </React.StrictMode>,
      document.getElementById('root')
    );
  }

  const handleFillAdd = () => {
    ReactDOM.render(
      <React.StrictMode>
          <FillAdd />
      </React.StrictMode>,
      document.getElementById('root')
    );
  }

  const handleDivision = () => {
    ReactDOM.render(
      <React.StrictMode>
          <Division />
      </React.StrictMode>,
      document.getElementById('root')
    );
  }

  const handleMultiply = () => {
    ReactDOM.render(
      <React.StrictMode>
          <Multiply />
      </React.StrictMode>,
      document.getElementById('root')
    );
  }

  const handleBuild = () => {
    ReactDOM.render(
      <React.StrictMode>
          <Building />
      </React.StrictMode>,
      document.getElementById('root')
    );
  }

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar
        position="fixed"
        className={clsx(classes.appBar, {
          [classes.appBarShift]: open,
        })}
      >
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            edge="start"
            className={clsx(classes.menuButton, open && classes.hide)}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h5" noWrap>
            Homework Time
          </Typography>
        </Toolbar>
      </AppBar>
      <Drawer
        className={classes.drawer}
        variant="persistent"
        anchor="left"
        open={open}
        classes={{
          paper: classes.drawerPaper,
        }}
      >
        <div className={classes.drawerHeader}>
          <IconButton onClick={handleDrawerClose}>
            {theme.direction === 'ltr' ? <ChevronLeftIcon /> : <ChevronRightIcon />}
          </IconButton>
        </div>
        <Divider />
        <List>
          <ListItem button key="Home">
            <ListItemText 
                primary="Home" 
                onClick={handleHome}/>
          </ListItem>
          <Divider />
          <ListItem button key="Addition">
            <ListItemText 
                primary="Addition" 
                onClick={handleAddition}/>
          </ListItem>
          <ListItem button key="Subtraction">
            <ListItemText 
                primary="Subtraction" 
                onClick={handleSubtraction}/>
          </ListItem>
          <ListItem button key="FillAdd">
            <ListItemText 
                primary="Fill Addition" 
                onClick={handleFillAdd}/>
          </ListItem>
          <ListItem button key="FillSub">
            <ListItemText 
                primary="Fill Subtraction" 
                onClick={handleFillSubtraction}/>
          </ListItem>
          <ListItem button key="Multiplication">
            <ListItemText 
                primary="Multiplication" 
                onClick={handleMultiply}/>
          </ListItem>
          <ListItem button key="Division">
            <ListItemText 
                primary="Division" 
                onClick={handleDivision}/>
          </ListItem>
        </List>
        
      </Drawer>
      <main
        className={clsx(classes.content, {
          [classes.contentShift]: open,
        })}
      >
        <div className={classes.drawerHeader} />
      </main>
    </div>
  );
}
