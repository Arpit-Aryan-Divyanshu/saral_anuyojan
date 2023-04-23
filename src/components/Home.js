import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import HealthAndSafetyIcon from '@mui/icons-material/HealthAndSafety';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { CircularProgress, IconButton, Slide } from '@mui/material';
import { Close } from '@mui/icons-material';
import ReactJson from 'react-json-view'


const theme = createTheme();
export default function Home() {
  const [result, setResult] = React.useState("Hello");
  const [checked, setChecked] = React.useState(false);
  const [loading, setLoading] = React.useState(false);
  const [query, setQuery] = React.useState("");
  const [queryName, setQueryName] = React.useState("");
  const [bg, setBg] = React.useState();
  const containerRef = React.useRef(null);
  React.useEffect(() => {
    fetch('https://api.unsplash.com/photos/random/?client_id=y7WEACJVLxo4M0KOSnQJxJa36IHUWOxhe5XZ6wF37FY&query=healthcare')
       .then((response) => response.json())
       .then((data) => {
          console.log(data.urls.regular);
          setBg(data.urls.regular);
       })
       .catch((err) => {
          console.log(err.message);
       });
   }, []);
  const handleSubmit = (event) => {
    setLoading(true);
    event.preventDefault();
    const data = new FormData(event.currentTarget);  
    setQuery(data.get('query'));
    setQueryName(data.get('query_name'));
    
    const addPosts = async () => {
      await fetch('http://localhost:8080/ehrbase/rest/openehr/v1/query/aql', {
      method: 'POST',
      body: JSON.stringify({
         "q": data.get('query'),
      }),
      headers: {
         'Content-type': 'application/json; charset=UTF-8',
      },
      })
      .then((response) =>response.json())
      .then((data) => {
         setResult(data);
        //  console.log(data);
      })
      .catch((err) => {
         console.log(err.message);
      });
      };
    addPosts();
    setLoading(false);
    setChecked(true);
  };
  
  const handleSaveQuery = () => {
        // const myq = {[queryName]:query};
        // localStorage.setItem("queries",JSON.stringify(myq));  
    
    const queries = JSON.parse(localStorage.getItem("queries")); 
    if(localStorage.getItem("queries")){
        queries[queryName] = query; 
        localStorage.setItem("queries",JSON.stringify(queries));  
    }else{
        const myq = {[queryName]:query};
        localStorage.setItem("queries",JSON.stringify(myq));  
    }
    console.log(queries);
  }
  return (
    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh' ,overflow:'hidden'}}>
        <CssBaseline />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              overflow: 'hidden',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            
            <a href="/">
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
              <HealthAndSafetyIcon />
            </Avatar>
            </a>
            <Typography component="h1" variant="h5">
              Saral Anuyojan
            </Typography>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
              <TextField
                margin="normal"
                required
                fullWidth
                multiline
                rows={4}
                maxRows={Infinity}
                id="query"
                label="Your Query"
                name="query"
                autoFocus
              />
              <TextField
                margin="normal"
                fullWidth
                name="query_name"
                label="Name of the query"
                id="query_name"
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 2, mb: 2 }}
              >
                Submit Query
              </Button>
              <div onClick={handleSaveQuery}>
              <Button
              type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 2, mb: 2 }}
              >
                Save Query
              </Button>
              </div>
              <a href="/savedquery">Saved Queries</a>
            </Box>
          </Box>
        </Grid>
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: `url(${bg})`,
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        >
          
          {loading ? <CircularProgress /> : 
          <Slide direction="up" in={checked} container={containerRef.current}>
                
                <Paper sx={{ 
                    my: 8,
                    mx: 4,
                    overflow: 'scroll',
                    height: '500px',
                    backgroundColor: "#1d1f21",
                    opacity:'10' }} elevation={4}>
                  <Box>
                  <Grid container spacing={2}>
                    <Grid item xs={11}>
                      <Typography variant='h5' align='center' color='white' style={{ paddingTop: '10px'}}>Output</Typography>
                    </Grid>
                    <Grid item xs={1}>         
                        <IconButton onClick={() => {setChecked(false);}}>
                          <Close sx={{color: "#acaeb0"}} elevation={4} ></Close>
                        </IconButton>
                    </Grid>
                  </Grid>
                    <Typography align='left' color='white' sx={{ paddingTop: '20px',paddingLeft: '20px',}}>
                        <ReactJson src={result} theme="tomorrow"/>
                    </Typography>
                  </Box>
                </Paper>
          </Slide>}
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}